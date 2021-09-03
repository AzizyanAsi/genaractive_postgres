package com.example.webik.models;

public class Configuration {
    public Resolution resolution;

    public Configuration(Resolution resolution) {
        this.resolution = resolution;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public enum Resolution {
        HD(1), FHD(2), K4(4);
        private int coefficient;

        Resolution(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }
    }
}
