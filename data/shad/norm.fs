#version 330

in vec2 textureCoords;
out vec4 color;

//uniform sampler2D image;
uniform vec3 texColor;

void main()
{
    //fragColor = vec4(texColor, 1.0) * texture(image, textureCoords);
    color = vec4(texColor, 1.0);
}