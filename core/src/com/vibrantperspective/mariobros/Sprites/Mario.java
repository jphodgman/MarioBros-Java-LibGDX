package com.vibrantperspective.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.vibrantperspective.mariobros.MarioBros;

/**
 * Created by jph on 1/25/2017.
 */

public class Mario extends Sprite {
    public World world;
    public Body b2body;
    public BodyDef bdef;
    public FixtureDef fdef;
    public CircleShape shape;

    public Mario(World world) {
        this.world = world;
        defineMario();
    }

    public void defineMario() {
        bdef = new BodyDef();
        bdef.position.set(32 / MarioBros.PPM, 32 / MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        fdef = new FixtureDef();
        shape = new CircleShape();
        shape.setRadius(5 / MarioBros.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


}
