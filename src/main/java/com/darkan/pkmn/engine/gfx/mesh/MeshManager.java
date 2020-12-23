package com.darkan.pkmn.engine.gfx.mesh;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

/**
 * Manages meshes for loading and using.
 *
 * Created by trent on 4/24/2018.
 */

public class MeshManager {
    //Constants for default square mesh.
    private static float[] SQUARE_VTX = {
            0f, 0f,
            0f, 1f,
            1f, 0f,
            1f, 1f,
    };

    private static float[] SQUARE_TEX = {
            0f, 1f,
            0f, 0f,
            1f, 1f,
            1f, 0f,
    };

    //Map to map meshes by name
    private static HashMap<String, Mesh> meshes = new HashMap<>();

    /**
     * Creates a new mesh manager and loads a generic square mesh
     * into it by default as a fallback.
     */
    public static void init() {
        unloadMeshes();
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
    }

    /**
     * Maps a mesh for use.
     *
     * @param name Name of mesh.
     * @param vertices Vertices for the mesh
     * @param textureCoords Texture coordinates for the mesh
     */
    public static void mapMesh(String name, float[] vertices, float[] textureCoords) {
        meshes.put(name, new Mesh(vertices, textureCoords));
    }

    /**
     * Gets a mesh by name from the map.
     * @param name The name of the mesh
     * @return The mesh
     */
    public static Mesh getMesh(String name) {
        return meshes.get(name);
    }

    /**
     * Gets the default square mesh from the map.
     * @return 2D square mesh
     */
    public static Mesh defaultMesh() {
        return meshes.get("square");
    }

    /**
     * Unload all the meshes and create a new map.
     */
    public static void unloadMeshes() {
        for (Mesh m : meshes.values()) {
            if (m != null)
                m.unload();
        }
        meshes = new HashMap<>();
        mapMesh("square", SQUARE_VTX, SQUARE_TEX);
    }
}
