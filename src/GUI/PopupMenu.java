package GUI;

import javax.swing.*;

/**
 * Created by id12jzn on 2015-12-17.
 * <p>
 * The popup menu that shows when the user presses the menu button in game
 * Does different things depending on users choice also pauses the game while the menu is up
 */
public class PopupMenu {
    private CLayout c;
    final JFrame popup = new JFrame();

    public PopupMenu(CLayout c) {
        this.c = c;
    }

    public void openPopup() {
        c.userinfo.pauseGame();

        Object[] options = {"Resume",
                "Restart",
                "Main menu"};

        int n = JOptionPane.showOptionDialog(popup,
                "Game is paused! Choose a option"
                        + "",
                "",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        // Resume
        if (n == 0) {
            c.userinfo.resumeGame();
        }
        // Restart
        if (n == 1) {
            c.userinfo.resumeGame();
            c.userinfo.killGame();
            c.game.restart();
            c.showGame(c.game.chosenMap);
        }
        // Menu
        if (n == 2) {
            c.game.gameStarted = false;
            c.userinfo.killGame();
            c.game.restart();
            c.showMainMenu();
        }
    }
}
