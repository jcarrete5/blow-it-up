package com.fwumdesoft.blow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Reflector extends DrawingActor {

	public float range;
	public int lane;

	public Reflector(int lane) {
		this(400, lane);
	}

	public Reflector(float range, int lane) {
		super(BlowItUp.assets.get("reflector.png", Texture.class));
		this.range = range;
		this.lane = lane;
		setRotation(lane * 45);
	}

	public void launchMissile() {
		Missile m1 = new Missile(1, getX(), getY(), lane); 
		m1.speed = 50;
		m1.setRotation(getRotation());
		getStage().addActor(m1);
	}

	public void reflectMissile(Missile m) {
		if (m.getRotation() != this.getRotation())
			m.setRotation(m.getRotation() + 180);
	}

	public void reflectMissiles() {
		Array<Actor> actors = getStage().getActors();
		for (Actor actor : actors) {
			if (actor instanceof Missile) {
				Missile m = (Missile) actor;
				if (m.lane != lane) 
					continue;
				System.out.println(Vector2.dst(getX(), getY(), m.getX(), m.getY()));
				if (Vector2.dst(getX(), getY(), m.getX(), m.getY()) <= range) {
					reflectMissile(m);
				}
			}
		}
	}

	@Override
	public void act(float delta) {

	}
}
