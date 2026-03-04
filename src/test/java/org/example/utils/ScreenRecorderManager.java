package org.example.utils;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.MediaType;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecorderManager {
    private ScreenRecorder screenRecorder;

    public void start(String testName) {
        try {
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            File videoDir = new File("target/recordings");
            if (!videoDir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                videoDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String safeTestName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");

            screenRecorder = new ScreenRecorder(
                    gc,
                    gc.getBounds(),
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, ENCODING_AVI_MJPG,
                            CompressorNameKey, ENCODING_AVI_MJPG,
                            DepthKey, 24,
                            FrameRateKey, Rational.valueOf(24),
                            QualityKey, 0.75f,
                            KeyFrameIntervalKey, 24 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null,
                    videoDir
            );

            screenRecorder.start();
            System.out.println("[Recorder] Started: " + safeTestName + "_" + timestamp + " (in target/recordings)");
        } catch (Exception e) {
            System.out.println("[Recorder] Failed to start: " + e.getMessage());
        }
    }

    public void stop() {
        if (screenRecorder == null) {
            return;
        }

        try {
            screenRecorder.stop();
            System.out.println("[Recorder] Stopped successfully");
        } catch (IOException e) {
            System.out.println("[Recorder] Failed to stop: " + e.getMessage());
        }
    }
}
