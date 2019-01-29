package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ball
{

    Background bg;
    EndGame EG;

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
    private boolean lose, won;


    public Ball()
    {

        bg = new Background();

        EG = new EndGame();

        XEndMap = 255;

        NumMap = 1;

        TransitTurn = 1;

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

        if (position.x <= -20)
        {

           switch (TransitTurn)
            {

                case 2: bg.pos.x = -7;

                        position.x = 550;

                        TransitTurn = 1;

                        break;

                case 3:
                        bg.pos.x = -1200;

                        position.x = 550;

                        TransitTurn = 2;

                        break;
            }

        }

        if(position.x >= 600 && TransitTurn < 3)
        {
            bg.pos.x = -600;

            position.x = 10;

            TransitTurn = 2;
        }

        if(position.x >= 550 && TransitTurn == 2)
        {
            bg.pos.x = -1200;

            position.x = 10;

            TransitTurn = 3;

        }

    }

    private void control()
    {

        if(!lose || !won)
        {

            if (Gdx.input.isKeyPressed(Input.Keys.W))
            {
                if (down)
                {
                    position.y += JumpH;
                    fly = true;
                    vy = JumpH;
                }
                down = false;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D))
                position.x += speed;

            if (!wall)
            {
                if (Gdx.input.isKeyPressed(Input.Keys.A))
                    position.x -= speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S))
            {
                JumpH += 1;
            }

        }

    }

    private void wall()
    {
        if(NumMap == 1 && TransitTurn == 1)
        {
            if (position.x <= 8)
                wall = true;
            else
                wall = false;
        }
    }


    private void restart()
    {

        lose = false;

        TransitTurn = 1;
        position.x = 100;
        position.y = 380;

        bg.pos.x = -7;

        YEndMap = 45;

        JumpH = 15;
        speed = 5;

        vx = vy = 0;
        gravity = -0.6f;


    }

    public void render(SpriteBatch batch)
    {

        bg.render(batch);
        batch.draw(img, position.x, position.y);

        if(position.x >= XEndMap && position.y <= 44 && TransitTurn == 3 || lose) //abbys
        {

            lose = true;

            YEndMap = -1000000;

            EG.lose(batch);

            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                restart();
        }

    }


    public void update()
    {

        bg.update();

        if(!lose || !won)
            control();

        wall();
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
