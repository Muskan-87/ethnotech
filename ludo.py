import pygame
import random
import sys

pygame.init()

# ── Constants ──────────────────────────────────────────────────────────────────
W, H       = 700, 750
CELL       = 46
BOARD_OFF  = 20          # board top-left offset
COLS       = 15

# Player colours  (Red, Green, Yellow, Blue)
COLORS     = [(220,50,50),(50,180,50),(220,180,0),(50,100,220)]
LIGHT      = [(255,180,180),(180,255,180),(255,240,150),(180,200,255)]
NAMES      = ["Red","Green","Yellow","Blue"]

# Safe cells (0-based index on the main path, 0..51)
SAFE_CELLS = {0,8,13,21,26,34,39,47}

# ── Main path (col, row) for each of the 52 squares ───────────────────────────
def build_main_path():
    path = []
    # Bottom-left corner going up  (col=6, rows 14..8)
    for r in range(14, 7, -1): path.append((6, r))
    # Top-left block going right   (rows 6, cols 0..5)
    for c in range(0, 6):      path.append((c, 6))
    # Up the left side             (col=0..5 done; now col=6 going up)
    for r in range(5, -1, -1): path.append((6, r))
    # Top row going right          (row=0, cols 7..14)
    for c in range(7, 15):     path.append((c, 0))   # wrong – fix below
    path = []                  # restart with correct layout

    # Standard Ludo 52-cell path (col, row) on a 15×15 grid
    # Red starts at index 0 (col 6, row 13)
    coords = [
        # going up col 6
        (6,14),(6,13),(6,12),(6,11),(6,10),(6,9),
        # turn left row 8
        (5,8),(4,8),(3,8),(2,8),(1,8),(0,8),
        # turn up col 0..6 → actually going right then up
        # row 6 going right
        (0,6),(1,6),(2,6),(3,6),(4,6),(5,6),
        # col 6 going up
        (6,5),(6,4),(6,3),(6,2),(6,1),(6,0),
        # row 0 going right
        (7,0),(8,0),
        # col 8 going down
        (8,1),(8,2),(8,3),(8,4),(8,5),
        # row 6 going right
        (8,6),(9,6),(10,6),(11,6),(12,6),(13,6),(14,6),
        # col 14 going down
        (14,8),
        # row 8 going left
        (13,8),(12,8),(11,8),(10,8),(9,8),
        # col 8 going down
        (8,9),(8,10),(8,11),(8,12),(8,13),(8,14),
        # row 14 going left
        (7,14),
        # back to start area
        (7,13),(7,12),(7,11),(7,10),(7,9),
        # row 7 going left (home column for red)
        (7,8),
    ]
    return coords

# Proper 52-cell path
MAIN_PATH = [
    (6,13),(6,12),(6,11),(6,10),(6,9),(6,8),   # 0-5  Red start col going up
    (5,7),(4,7),(3,7),(2,7),(1,7),(0,7),         # 6-11
    (0,6),(1,6),(2,6),(3,6),(4,6),(5,6),         # 12-17
    (6,5),(6,4),(6,3),(6,2),(6,1),(6,0),         # 18-23
    (7,0),(8,0),                                  # 24-25
    (8,1),(8,2),(8,3),(8,4),(8,5),(8,6),         # 26-31
    (9,6),(10,6),(11,6),(12,6),(13,6),(14,6),    # 32-37
    (14,7),(14,8),                                # 38-39
    (13,8),(12,8),(11,8),(10,8),(9,8),(8,8),     # 40-45
    (8,9),(8,10),(8,11),(8,12),(8,13),(8,14),    # 46-51
]  # 52 cells total

# Home-column paths (6 cells each, leading to centre)
HOME_COLS = [
    [(1,7),(2,7),(3,7),(4,7),(5,7),(6,7)],   # Red   enters at index 0 → moves right
    [(7,1),(7,2),(7,3),(7,4),(7,5),(7,6)],   # Green enters at index 13 → moves down
    [(13,7),(12,7),(11,7),(10,7),(9,7),(8,7)],# Yellow enters at index 26 → moves left
    [(7,13),(7,12),(7,11),(7,10),(7,9),(7,8)],# Blue  enters at index 39 → moves up
]

# Entry index on MAIN_PATH where each player starts
START_IDX  = [0, 13, 26, 39]
# Index just before home column (player enters home col after this)
HOME_ENTRY = [51, 12, 25, 38]   # last main-path cell before home col

# Yard positions (col, row) for 4 tokens of each player
YARDS = [
    [(1,11),(3,11),(1,13),(3,13)],   # Red
    [(11,1),(13,1),(11,3),(13,3)],   # Green
    [(11,11),(13,11),(11,13),(13,13)],# Yellow
    [(1,1),(3,1),(1,3),(3,3)],       # Blue
]

# ── Token ──────────────────────────────────────────────────────────────────────
class Token:
    def __init__(self, player, idx):
        self.player   = player   # 0-3
        self.idx      = idx      # 0-3 (token number)
        self.pos      = -1       # -1 = yard; 0..51 = main path; 52..57 = home col
        self.finished = False

    def board_cell(self):
        """Return (col, row) grid cell for drawing."""
        if self.finished:
            return (7, 7)        # centre
        if self.pos == -1:
            return YARDS[self.player][self.idx]
        if self.pos <= 51:
            return MAIN_PATH[self.pos % 52]
        # home column
        hc_idx = self.pos - 52  # 0..5
        return HOME_COLS[self.player][hc_idx]

    def can_move(self, dice):
        if self.finished:
            return False
        if self.pos == -1:
            return dice == 6
        steps_left = 57 - self.pos   # 57 = fully home (after 6 home-col cells)
        return dice <= steps_left

    def move(self, dice):
        if self.pos == -1 and dice == 6:
            self.pos = START_IDX[self.player]
            return
        self.pos += dice
        if self.pos >= 58:
            self.pos = 57
        if self.pos == 57:
            self.finished = True

# ── Game ───────────────────────────────────────────────────────────────────────
class Ludo:
    def __init__(self):
        self.screen  = pygame.display.set_mode((W, H))
        pygame.display.set_caption("Ludo")
        self.clock   = pygame.time.Clock()
        self.font    = pygame.font.SysFont("Arial", 18, bold=True)
        self.big     = pygame.font.SysFont("Arial", 28, bold=True)

        self.tokens  = [[Token(p, i) for i in range(4)] for p in range(4)]
        self.turn    = 0
        self.dice    = 0
        self.rolled  = False
        self.movable = []
        self.winner  = None
        self.msg     = "Red's turn — Press SPACE to roll"

    # ── helpers ────────────────────────────────────────────────────────────────
    def cell_px(self, col, row):
        """Top-left pixel of a grid cell."""
        return (BOARD_OFF + col * CELL, BOARD_OFF + row * CELL)

    def cell_center(self, col, row):
        x, y = self.cell_px(col, row)
        return (x + CELL // 2, y + CELL // 2)

    def draw_board(self):
        self.screen.fill((240, 230, 210))
        # Draw 15×15 grid cells
        for r in range(15):
            for c in range(15):
                rect = pygame.Rect(BOARD_OFF + c*CELL, BOARD_OFF + r*CELL, CELL, CELL)
                # Yard zones
                color = (255,255,255)
                if r < 6 and c < 6:   color = LIGHT[0]   # Red yard
                if r < 6 and c > 8:   color = LIGHT[1]   # Green yard
                if r > 8 and c > 8:   color = LIGHT[2]   # Yellow yard
                if r > 8 and c < 6:   color = LIGHT[3]   # Blue yard
                # Centre triangle colours
                if 6 <= r <= 8 and 6 <= c <= 8:
                    color = (255,255,255)
                pygame.draw.rect(self.screen, color, rect)
                pygame.draw.rect(self.screen, (180,170,150), rect, 1)

        # Colour the home columns
        hc_colors = [COLORS[0], COLORS[1], COLORS[2], COLORS[3]]
        for p, hc in enumerate(HOME_COLS):
            for (c, r) in hc:
                rect = pygame.Rect(BOARD_OFF + c*CELL, BOARD_OFF + r*CELL, CELL, CELL)
                pygame.draw.rect(self.screen, LIGHT[p], rect)
                pygame.draw.rect(self.screen, (180,170,150), rect, 1)

        # Colour start cells
        for p in range(4):
            c, r = MAIN_PATH[START_IDX[p]]
            rect = pygame.Rect(BOARD_OFF + c*CELL, BOARD_OFF + r*CELL, CELL, CELL)
            pygame.draw.rect(self.screen, COLORS[p], rect)
            pygame.draw.rect(self.screen, (0,0,0), rect, 1)

        # Safe cell stars
        for idx in SAFE_CELLS:
            c, r = MAIN_PATH[idx]
            cx, cy = self.cell_center(c, r)
            self.draw_star(cx, cy, 10, (200,200,0))

        # Centre square
        cx, cy = self.cell_center(7, 7)
        pygame.draw.polygon(self.screen, COLORS[0], [
            (BOARD_OFF+6*CELL, BOARD_OFF+6*CELL),
            (BOARD_OFF+7*CELL+CELL//2, BOARD_OFF+7*CELL),
            (BOARD_OFF+6*CELL, BOARD_OFF+9*CELL)])
        pygame.draw.polygon(self.screen, COLORS[1], [
            (BOARD_OFF+6*CELL, BOARD_OFF+6*CELL),
            (BOARD_OFF+9*CELL, BOARD_OFF+6*CELL),
            (BOARD_OFF+7*CELL+CELL//2-CELL//2, BOARD_OFF+7*CELL+CELL//2-CELL//2)])
        pygame.draw.polygon(self.screen, COLORS[2], [
            (BOARD_OFF+9*CELL, BOARD_OFF+6*CELL),
            (BOARD_OFF+9*CELL, BOARD_OFF+9*CELL),
            (BOARD_OFF+7*CELL+CELL//2-CELL//2, BOARD_OFF+7*CELL+CELL//2-CELL//2)])
        pygame.draw.polygon(self.screen, COLORS[3], [
            (BOARD_OFF+6*CELL, BOARD_OFF+9*CELL),
            (BOARD_OFF+9*CELL, BOARD_OFF+9*CELL),
            (BOARD_OFF+7*CELL+CELL//2-CELL//2, BOARD_OFF+7*CELL+CELL//2-CELL//2)])

    def draw_star(self, x, y, r, color):
        import math
        pts = []
        for i in range(10):
            angle = math.pi/2 + i * math.pi/5
            rad   = r if i % 2 == 0 else r//2
            pts.append((x + rad*math.cos(angle), y - rad*math.sin(angle)))
        pygame.draw.polygon(self.screen, color, pts)

    def draw_tokens(self):
        for p in range(4):
            for t in self.tokens[p]:
                c, r   = t.board_cell()
                cx, cy = self.cell_center(c, r)
                # offset tokens sharing same cell
                offset = [(-8,-8),(8,-8),(-8,8),(8,8)][t.idx]
                cx += offset[0]; cy += offset[1]
                pygame.draw.circle(self.screen, COLORS[p], (cx, cy), 14)
                pygame.draw.circle(self.screen, (0,0,0),   (cx, cy), 14, 2)
                lbl = self.font.render(str(t.idx+1), True, (255,255,255))
                self.screen.blit(lbl, lbl.get_rect(center=(cx, cy)))
                # highlight movable
                if t in self.movable:
                    pygame.draw.circle(self.screen, (255,255,0), (cx, cy), 17, 3)

    def draw_ui(self):
        # Panel below board
        panel_y = BOARD_OFF + 15*CELL + 5
        pygame.draw.rect(self.screen, (50,50,50), (0, panel_y, W, H - panel_y))

        # Dice
        dice_x, dice_y = 20, panel_y + 10
        pygame.draw.rect(self.screen, (255,255,255), (dice_x, dice_y, 60, 60), border_radius=8)
        if self.dice:
            self.draw_dice_face(dice_x, dice_y, self.dice)

        # Message
        msg_surf = self.font.render(self.msg, True, (255,255,255))
        self.screen.blit(msg_surf, (100, panel_y + 25))

        # Turn indicator
        ind = pygame.Rect(W-80, panel_y+10, 60, 60)
        pygame.draw.rect(self.screen, COLORS[self.turn], ind, border_radius=8)
        lbl = self.big.render(NAMES[self.turn][0], True, (255,255,255))
        self.screen.blit(lbl, lbl.get_rect(center=ind.center))

        if self.winner is not None:
            overlay = pygame.Surface((W, H), pygame.SRCALPHA)
            overlay.fill((0,0,0,160))
            self.screen.blit(overlay, (0,0))
            txt = self.big.render(f"🎉 {NAMES[self.winner]} WINS! 🎉", True, COLORS[self.winner])
            self.screen.blit(txt, txt.get_rect(center=(W//2, H//2)))
            sub = self.font.render("Press R to restart", True, (255,255,255))
            self.screen.blit(sub, sub.get_rect(center=(W//2, H//2+40)))

    def draw_dice_face(self, x, y, val):
        dots = {
            1: [(30,30)],
            2: [(15,15),(45,45)],
            3: [(15,15),(30,30),(45,45)],
            4: [(15,15),(45,15),(15,45),(45,45)],
            5: [(15,15),(45,15),(30,30),(15,45),(45,45)],
            6: [(15,15),(45,15),(15,30),(45,30),(15,45),(45,45)],
        }
        for dx, dy in dots[val]:
            pygame.draw.circle(self.screen, (30,30,30), (x+dx, y+dy), 5)

    # ── logic ──────────────────────────────────────────────────────────────────
    def roll(self):
        self.dice   = random.randint(1, 6)
        self.rolled = True
        self.movable = [t for t in self.tokens[self.turn] if t.can_move(self.dice)]
        if not self.movable:
            self.msg = f"{NAMES[self.turn]} rolled {self.dice} — no moves, next turn"
            pygame.time.set_timer(pygame.USEREVENT, 1200)
        else:
            self.msg = f"{NAMES[self.turn]} rolled {self.dice} — click a token"

    def apply_move(self, token):
        token.move(self.dice)
        self.capture(token)
        self.rolled  = False
        self.movable = []
        if self.check_winner():
            return
        # Extra turn on 6
        if self.dice == 6:
            self.msg = f"{NAMES[self.turn]} rolled 6 — roll again!"
        else:
            self.turn = (self.turn + 1) % 4
            self.msg  = f"{NAMES[self.turn]}'s turn — Press SPACE to roll"

    def capture(self, moved):
        if moved.pos < 0 or moved.pos > 51 or moved.pos in SAFE_CELLS:
            return
        cell = MAIN_PATH[moved.pos]
        for p in range(4):
            if p == moved.player:
                continue
            for t in self.tokens[p]:
                if t.pos >= 0 and t.pos <= 51 and MAIN_PATH[t.pos] == cell:
                    t.pos = -1   # send home
                    self.msg = f"{NAMES[moved.player]} captured {NAMES[p]}!"

    def check_winner(self):
        for p in range(4):
            if all(t.finished for t in self.tokens[p]):
                self.winner = p
                self.msg    = ""
                return True
        return False

    def next_turn(self):
        self.turn   = (self.turn + 1) % 4
        self.rolled = False
        self.msg    = f"{NAMES[self.turn]}'s turn — Press SPACE to roll"

    def reset(self):
        self.__init__()

    # ── main loop ──────────────────────────────────────────────────────────────
    def run(self):
        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit(); sys.exit()

                if event.type == pygame.USEREVENT:
                    pygame.time.set_timer(pygame.USEREVENT, 0)
                    self.next_turn()

                if self.winner is not None:
                    if event.type == pygame.KEYDOWN and event.key == pygame.K_r:
                        self.reset()
                    continue

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_SPACE and not self.rolled:
                        self.roll()

                if event.type == pygame.MOUSEBUTTONDOWN and self.rolled and self.movable:
                    mx, my = event.pos
                    for t in self.movable:
                        c, r   = t.board_cell()
                        cx, cy = self.cell_center(c, r)
                        off    = [(-8,-8),(8,-8),(-8,8),(8,8)][t.idx]
                        tx, ty = cx + off[0], cy + off[1]
                        if (mx-tx)**2 + (my-ty)**2 <= 18**2:
                            self.apply_move(t)
                            break

            self.draw_board()
            self.draw_tokens()
            self.draw_ui()
            pygame.display.flip()
            self.clock.tick(60)

if __name__ == "__main__":
    Ludo().run()
