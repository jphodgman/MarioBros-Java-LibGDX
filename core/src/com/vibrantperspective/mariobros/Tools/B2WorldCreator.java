package com.vibrantperspective.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vibrantperspective.mariobros.MarioBros;
import com.vibrantperspective.mariobros.Screens.PlayScreen;
import com.vibrantperspective.mariobros.Sprites.Brick;
import com.vibrantperspective.mariobros.Sprites.Coin;

/**
 * Created by jph on 1/25/2017.
 */

public class B2WorldCreator {

    public B2WorldCreator(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        Fixture fixture;

        //pull in all solid objects in tilemap

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef = new BodyDef();
            fdef = new FixtureDef();
            shape = new PolygonShape();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MarioBros.PPM, (rect.getY() + rect.getHeight()/2) / MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth()/2)/ MarioBros.PPM, (rect.getHeight()/2) / MarioBros.PPM);
            fdef.shape = shape;
            fixture = body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef = new BodyDef();
            fdef = new FixtureDef();
            shape = new PolygonShape();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / MarioBros.PPM, (rect.getY() + rect.getHeight()/2) / MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth()/2)/ MarioBros.PPM, (rect.getHeight()/2) / MarioBros.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
            fixture = body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(screen, rect);
        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(screen, rect);
        }
    }
}
