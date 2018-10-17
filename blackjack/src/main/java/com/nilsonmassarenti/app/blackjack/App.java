package com.nilsonmassarenti.app.blackjack;

import com.nilsonmassarenti.app.blackjack.controller.PlayController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PlayController playController = new PlayController();
        playController.playTheGame();
    }
}
