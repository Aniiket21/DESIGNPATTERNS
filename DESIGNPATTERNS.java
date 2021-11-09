import java.io.File;


class Demo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        // ...
    }
}

class DESIGPATTERNS{
    private String name;
    private String codecType;

    public DESIGPATTERNS(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() {
        return codecType;
    }

    public String getName() {
        return name;
    }
}

interface Codec {
}

class MPEG4CompressionCodec implements Codec {
    public String type = "mp4";

}

class OggCompressionCodec implements Codec {
    public String type = "ogg";
}
class CodecFactory {
    public static Codec extract( DESIGPATTERNS file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4CompressionCodec();
        }
        else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}

class BitrateReader {
    public static DESIGPATTERNS read(DESIGPATTERNS file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        return file;
    }

    public static DESIGPATTERNS convert(DESIGPATTERNS buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        return buffer;
    }

}

class AudioMixer {

    public File fix(DESIGPATTERNS result) {
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}
class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        DESIGPATTERNS file = new DESIGPATTERNS(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new OggCompressionCodec();
        } else {
            destinationCodec = new MPEG4CompressionCodec();
        }
        DESIGPATTERNS buffer = BitrateReader.read(file, sourceCodec);
        DESIGPATTERNS intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}