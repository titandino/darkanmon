package com.darkan.pkmn.engine.render;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Class to represent a mesh that can be bound when an entity is rendered.
 * Created by trent on 4/18/2018.
 */
public class Mesh {
    private float[] vertices;
    private float[] texCoords;
    private int vertexVao;
    private int textureVao;
    private boolean loaded;
    private boolean bound;

    /**
     * Creates a mesh with the specified vertices and texture coordinates
     * @param vertices Vertices of the mesh
     * @param texCoords Texture coordinates of the mesh
     */
    public Mesh(float[] vertices, float[] texCoords) {
        this.vertices = vertices;
        this.texCoords = texCoords;
    }

    /**
     * Loads the mesh into the buffers.
     */
    public Mesh load() {
        if (loaded)
            return this;
        //Generate 2 buffers to hold the mesh data
        vertexVao = glGenBuffers();
        textureVao = glGenBuffers();

        //Insert vertices into VAO
        glBindBuffer(GL_ARRAY_BUFFER, vertexVao);
        FloatBuffer verticesBuf = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertices);
        verticesBuf.position(0);
        glBufferData(GL_ARRAY_BUFFER, verticesBuf, GL_STATIC_DRAW);

        //Insert texture coordinates into VAO
        glBindBuffer(GL_ARRAY_BUFFER, textureVao);
        FloatBuffer texCoordsBuf = ByteBuffer.allocateDirect(texCoords.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(texCoords);
        texCoordsBuf.position(0);
        glBufferData(GL_ARRAY_BUFFER, texCoordsBuf, GL_STATIC_DRAW);
        loaded = true;
        return this;
    }

    /**
     * Binds the mesh for drawing.
     */
    public void bind() {
        if (bound)
            return;
        load();

        //Use this mesh's vertices in VAO #0
        glBindBuffer(GL_ARRAY_BUFFER, vertexVao);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

        //Use this mesh's texture coordinates VAO #1
        glBindBuffer(GL_ARRAY_BUFFER, textureVao);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        bound = true;
    }

    /**
     * Unloads the mesh's buffers
     */
    public void unload() {
        if (!loaded)
            return;
        loaded = false;
        bound = false;
        glDeleteBuffers(vertexVao);
        glDeleteBuffers(textureVao);
        return;
    }
}
