package com.luismichu.grey_controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GreyController extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private ShapeRenderer sR;
	public static boolean toc;
	public static int x, y;
	private Client cliente;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sR = new ShapeRenderer();
		toc = false;
		Gdx.input.setInputProcessor(this);
		cliente = new Client();
		cliente.start();
		FBConexion fb = new FBConexion();
		fb.start();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		update();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cliente.dispose();
	}

	private void update(){
		Color c1 = Color.BLACK, c2 = Color.BLACK;
		if(toc){
			if(x < Gdx.graphics.getWidth()/2){
				c1 = Color.GREEN;
				c2 = Color.BLACK;
			}
			else{
				c1 = Color.BLACK;
				c2 = Color.GREEN;
			}
		}
		drawRect(c1, c2);
	}

	private void drawRect(Color c1, Color c2){
		sR.begin(ShapeRenderer.ShapeType.Filled);
		sR.setColor(c1);
		sR.rect(40, 40, Gdx.graphics.getWidth()/2 - 60, Gdx.graphics.getHeight() - 80);
		sR.setColor(c2);
		sR.rect(Gdx.graphics.getWidth()/2 + 20, 40, Gdx.graphics.getWidth()/2 - 60, Gdx.graphics.getHeight() - 80);
		sR.end();
	}

	@Override
	public boolean keyDown(int keycode) { return false;	}

	@Override
	public boolean keyUp(int keycode) {	return false; }

	@Override
	public boolean keyTyped(char character) { return false; }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		x = screenX;
		y = screenY;
		toc = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		toc  = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false;	}

	@Override
	public boolean scrolled(int amount) { return false;	}
}
