package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ball
{

    Background bg;

    private Texture img;
    private Vector2 position;
    private float vx, vy;
    private float gravity;
    private float speed;
    private boolean fly, down;
    private float JumpH;
    private int TransitTurn;
    private int XEndMap, YEndMap;
    private int NumMap;
    private boolean wall;


    public Ball()
    {

        bg = new Background();

        TransitTurn = 1;
        NumMap = 1;

        XEndMap = 355;

        YEndMap = 45;

        img = new Texture("ball.png");
        position = new Vector2(100, 380);

        fly = true;

        JumpH = 15;
        speed = 5;

        vx = vy = 0;
        gravity = -0.6f;

    }


    private void transition()
    {

        if(TransitTurn == 2)
        {
            if (position.x == -20)
            {
                bg.pos.x = -7;

                position.x = 550;

                TransitTurn = 1;
            }
        }

        if(position.x == 600)
        {
            bg.pos.x = -600;

            position.x = 10;

            TransitTurn = 2;
        }

    }

    private void control()
    {

        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            if(down)
            {
                position.y += JumpH;
                fly = true;
                vy = JumpH;
            }
            down = false;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D))
            position.x += speed;

        if(!wall)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.A))
                position.x -= speed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            vy -= 0.1f;


    }

    private void wall()
    {
        if(NumMap == 1 && TransitTurn == 1)
        {
            if (position.x == -10)
                wall = true;
            else
                wall = false;
        }
    }

    private void abyss()
    {

        if(position.x == XEndMap && TransitTurn == 2)
        {
            YEndMap = -1000000;
        }

    }

    public void render(SpriteBatch batch)
    {

        bg.render(batch);
        batch.draw(img, position.x, position.y);

    }


    public void update()
    {

        bg.update();

        wall();
        abyss();
        control();
        transition();

        if(position.y <= YEndMap) // Restrictions
        {
            down = true;
            fly = false;

            vy = 0.00f;
        }
        else fly = true;

        if(fly)
        {
            vy += gravity;
            position.y += vy;
        }

    }

}
