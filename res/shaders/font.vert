#version 330

precision mediump float;

const float degToRad = 3.1415926535897932384626433832795f / 180.0f;

uniform mat4 ortho;
uniform vec2 translation;
uniform float rotation;
uniform vec2 scale;

layout (location = 0) in vec4 positionIn;
layout (location = 1) in vec4 texCoordIn;

out vec4 texCoord;

mat4 scaleMtx(vec2 s) {
    mat4 m;
    m[0][0] = s.x;  m[1][0] = 0.0f;  m[2][0] = 0.0f;  m[3][0] = 0.0f;
    m[0][1] = 0.0f;  m[1][1] = s.y;  m[2][1] = 0.0f;  m[3][1] = 0.0f;
    m[0][2] = 0.0f;  m[1][2] = 0.0f;  m[2][2] = 1.0f;  m[3][2] = 0.0f;
    m[0][3] = 0.0f;  m[1][3] = 0.0f;  m[2][3] = 0.0f;  m[3][3] = 1.0f;
    return m;
}

mat4 translateMtx(vec2 t) {
    mat4 m;
    m[0][0] = 1.0f;  m[1][0] = 0.0f;  m[2][0] = 0.0f;  m[3][0] = t.x;
    m[0][1] = 0.0f;  m[1][1] = 1.0f;  m[2][1] = 0.0f;  m[3][1] = t.y;
    m[0][2] = 0.0f;  m[1][2] = 0.0f;  m[2][2] = 1.0f;  m[3][2] = 0.0f;
    m[0][3] = 0.0f;  m[1][3] = 0.0f;  m[2][3] = 0.0f;  m[3][3] = 1.0f;
    return m;
}

mat4 rotateZMtx(float a) {
    mat4 m;
    m[0][0] = cos(a);  m[1][0] = -sin(a);  m[2][0] = 0.0f;  m[3][0] = 0.0f;
    m[0][1] = sin(a);  m[1][1] = cos(a);  m[2][1] = 0.0f;  m[3][1] = 0.0f;
    m[0][2] = 0.0f;  m[1][2] = 0.0f;  m[2][2] = 1.0f;  m[3][2] = 0.0f;
    m[0][3] = 0.0f;  m[1][3] = 0.0f;  m[2][3] = 0.0f;  m[3][3] = 1.0f;
    return m;
}

void main() {
    texCoord = texCoordIn;
    mat4 transform = translateMtx(translation) * rotateZMtx(rotation*degToRad) * scaleMtx(scale);
    gl_Position = ortho * transform * vec4(positionIn.xy, 0.0, 1.0);
}
