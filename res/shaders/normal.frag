#version 330

precision mediump float;

uniform sampler2D tex;
uniform sampler2D heightMap;
uniform int height;
uniform vec4 color;
uniform bool flip;

in vec4 texCoord;
out vec4 finalColor;

void main(void) {
    if (flip)
        finalColor = texture(tex, vec2(texCoord.x, abs(texCoord.y - 1.0f)));
    else
        finalColor = texture(tex, texCoord.xy);
    if (color.x < 1.0f) {
        finalColor = mix(color, finalColor, 0.5f);
    }
    if (height != 1000) {
        vec2 position = gl_FragCoord.xy / vec2(3840.0f, 2160.0f);
        vec4 color = texture(heightMap, vec2(position.x, abs(position.y - 1.0f)));
        if (float(height)/255.0f < color.r) {
            finalColor = vec4(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }
}
