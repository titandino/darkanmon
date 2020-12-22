package com.darkan.pkmn.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.awt.Color;

import com.darkan.pkmn.engine.render.FBO;
import com.darkan.pkmn.engine.render.Mesh;
import com.darkan.pkmn.engine.render.Shader;
import com.darkan.pkmn.engine.render.Texture;
import com.darkan.pkmn.engine.util.Vector2f;

/**
 * Created by trent on 4/6/2018.
 *
 * Represents a rectangular 2D entity that has a texture to bind to it
 * when rendered.
 */
public class Entity {
    //Transform variables
    private Vector2f position;
    private float rotation;
    private Vector2f scale;

    private Vector2f velocity;

    //Texture and texture coordinates
    private Mesh mesh;
    private Texture texture;
    private boolean texFbo;
    private Color color;
    private int height = 0;

    public Entity(Vector2f position, float width, float height, Mesh mesh, Texture texture) {
        this(position, new Vector2f(0, 0), width, height, mesh, texture);
    }

    /**
     * Initialize the entity.
     *
     * @param position Position of the entity.
     * @param velocity Velocity to give at the start.
     * @param width Width of rectangle.
     * @param height Height of rectangle.
     * @param texture Texture to bind to the entity.
     */
    public Entity(Vector2f position, Vector2f velocity, float width, float height, Mesh mesh, Texture texture) {
        this.position = position;
        this.velocity = velocity;
        this.scale = new Vector2f(width, height);
        this.rotation = 0;
        this.mesh = mesh;
        this.texture = texture;
        this.texFbo = texture instanceof FBO;
    }

    /**
     * Override method for handling custom logic
     * @param delta time passed since last update
     */
    public void update(float delta) {  }

    /**
     * Update the entity based on time.
     * @param delta time passed since last update
     */
    public final void _update(float delta) {
        update(delta);
        position = position.add(velocity.scale(delta));
    }

    /**
     * Override for adding extra render features.
     * @param shader Shader to bind when rendering.
     */
    public void render(Shader shader) { }

    /**
     * Render the entity using the shader provided.
     * @param shader Shader to bind when rendering.
     */
    public final void _render(Shader shader) {
        mesh.bind();
        texture.bind(shader.getUniformLocation("tex"));

        //Flip y axis of texture coordinates if it is an FBO as a texture
        glUniform1i(shader.getUniformLocation("flip"), texFbo ? 1 : 0);
        glUniform1i(shader.getUniformLocation("height"), height);

        glUniform4fv(shader.getUniformLocation("color"), new float[] { color == null ? 2.0f : color.getRed() / 255f, color == null ? 2.0f : color.getGreen() / 255f, color == null ? 2.0f : color.getBlue() / 255f, color == null ? 2.0f : color.getAlpha() / 255f });

        //Pass transformation to shader
        glUniform2fv(shader.getUniformLocation("translation"), new float[] { position.x, position.y });
        glUniform1f(shader.getUniformLocation("rotation"), rotation);
        glUniform2fv(shader.getUniformLocation("scale"), new float[] { scale.x, scale.y });

        //Draw the entity
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }

    /**
     * Check whether a point is within the rectangular area of the entity.
     * @param point Point to check
     * @return Whether the point is within the rectangle
     */
    public boolean collides(Vector2f point) {
        if (point.x > (position.x+scale.x/2))
            return false;
        if (point.x < (position.x-scale.x/2))
            return false;
        if (point.y > (position.y+scale.y/2))
            return false;
        if (point.y < (position.y-scale.y/2))
            return false;
        return true;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
