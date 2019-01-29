package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EndGame
{

    private Texture lose;
    private Texture win;
    private Vector2 position;

    public EndGame()
    {

        lose = new Texture("RestartBtn.png");
        position = new Vector2(100, 100);

    }

    public void lose(SpriteBatch batch)
    {
        batch.draw(lose, position.x, position.y);
    }

    public void win()
    {}
}
