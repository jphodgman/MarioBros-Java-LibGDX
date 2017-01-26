package com.vibrantperspective.mariobros.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vibrantperspective.mariobros.MarioBros;
import com.vibrantperspective.mariobros.Scenes.Hud;

import javax.swing.text.View;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

/**
 * Created by jph on 1/24/2017.
 */

public class PlayScreen implements Screen {
    private MarioBros game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(MarioBros game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        renderer.setBlending(true);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //pull in all solid objects in tilemap

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if(Gdx.input.isTouched())
            gamecam.position.x += 100 * dt;
    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
