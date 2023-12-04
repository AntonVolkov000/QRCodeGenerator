public class Enums {
    public enum EncodingType {
        DIGITAL(0, "0001"),
        ALPHANUMERIC(1, "0010"),
        BYTE(2, "0100");

        private final int encodingIndex;
        private final String encodingBits;

        EncodingType(int encodingIndex, String encodingBits) {
            this.encodingIndex = encodingIndex;
            this.encodingBits = encodingBits;
        }

        public int getEncodingIndex() {
            return encodingIndex;
        }

        public String getEncodingBits() {
            return encodingBits;
        }
    }

    public enum CorrectionLevel {
        L(0),
        M(1),
        Q(2),
        H(3);

        private final int levelIndex;

        CorrectionLevel(int levelIndex) {
            this.levelIndex = levelIndex;
        }

        public int getLevelIndex() {
            return levelIndex;
        }
    }

    public enum VersionType {
        VERSION_1_9(0, 1, 9),
        VERSION_10_26(1, 10, 26),
        VERSION_27_40(2, 27, 40);

        private final int versionTypeIndex;
        private final int startVersion;
        private final int endVersion;

        VersionType(int versionTypeIndex, int startVersion, int endVersion) {
            this.versionTypeIndex = versionTypeIndex;
            this.startVersion = startVersion;
            this.endVersion = endVersion;
        }

        public int getVersionTypeIndex() {
            return versionTypeIndex;
        }

        public int getStartVersion() {
            return startVersion;
        }

        public int getEndVersion() {
            return endVersion;
        }

        public static VersionType getTypeByVersion(int qrCodeVersion) {
            for (VersionType versionType : VersionType.values()) {
                if (qrCodeVersion >= versionType.getStartVersion()
                        && qrCodeVersion <= versionType.getEndVersion()) {
                    return versionType;
                }
            }
            throw new IllegalArgumentException("Нет подходящей версии qr-кода, входные данные слишком большие");
        }
    }
}
