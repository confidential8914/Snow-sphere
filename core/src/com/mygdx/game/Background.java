package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background
{

    private Texture img;
    public Vector2 pos;
    private int speed;

    public Background()
    {

        img = new Texture( "P_snow.png");
        pos = new Vector2(-7,0);
        speed = 4;

    }

    public void render(SpriteBatch batch)
    {
        batch.draw(img, pos.x, pos.y);
    }

    public void update()
    {
    }

}
