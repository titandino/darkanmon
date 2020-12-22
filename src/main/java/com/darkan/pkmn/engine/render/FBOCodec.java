package com.darkan.pkmn.engine.render;
//package com.enow.sim.engine.render;
//
//import java.io.IOException;
//
//public class FBOCodec extends FBO {
//
//    private MediaCodec codec;
//
//    /**
//     * Initializes an FBO with certain width and height. Will call all necessary
//     * GL initialization functions needed upon creation.
//     *
//     * @param width  Width of the texture to render to.
//     * @param height Height of the texture to render to.
//     */
//    public FBOCodec(int width, int height, int bitrate, int fps, int iframe, MediaCodec.Callback callback) {
//        super(width, height);
//        MediaFormat format = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, width, height);
//
//        format.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
//        format.setInteger(MediaFormat.KEY_FRAME_RATE, fps);
//        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
//        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframe);
//
//        try {
//            codec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        codec.setCallback(callback);
//        codec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
//    }
//}
