#version 330

in vec4 texCoord;

out vec4 outColor;

uniform vec4 color;
uniform sampler2D fontAtlas;

void main(void) {
	outColor = texture(fontAtlas, texCoord.xy);
	outColor = vec4(color.xyz, outColor.a);
}
