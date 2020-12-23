#version 330

precision mediump float;

uniform sampler2D tex;
uniform vec4 color;
uniform bool flip;

in vec4 texCoord;
out vec4 finalColor;

void main(void) {
    if (flip)
        finalColor = texture(tex, vec2(texCoord.x, abs(texCoord.y - 1.0f)));
    else
        finalColor = texture(tex, texCoord.xy);
    if (color.x <= 1.0f) {
        finalColor = mix(color, finalColor, 0.5f);
    }
}
