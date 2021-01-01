#version 330

in vec4 texCoord;

out vec4 outColor;

uniform vec3 color;
uniform vec3 borderColor;
uniform sampler2D fontAtlas;

uniform float smoothingWidth = 0.47;
uniform float smoothingEdge = 0.11;
uniform float borderWidth = 0.45;
uniform float borderEdge = 0.35;
uniform vec2 borderOffset = vec2(0.006, 0.006);

void main(void) {
	float distance = 1.0 - texture(fontAtlas, texCoord.xy).a;
	float alpha = 1.0 - smoothstep(smoothingWidth, smoothingWidth + smoothingEdge, distance);
	float borderDist = 1.0 - texture(fontAtlas, texCoord.xy + borderOffset).a;
	float borderAlpha = 1.0 - smoothstep(borderWidth, borderWidth + borderEdge, borderDist);

	float finalAlpha = alpha + (1.0 - alpha) * borderAlpha;

	outColor = vec4(mix(borderColor, color, alpha / finalAlpha), finalAlpha);
}
