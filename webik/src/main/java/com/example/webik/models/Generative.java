package com.example.webik.models;

public class Generative extends Item {
    double complexity;

    public Generative(String id, String name, double complexity, double price, Configuration configuration) {
        super(id, name, price, configuration);
        this.complexity = complexity;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('{')
                .append(id)
                .append("_")
                .append(name)
                .append("_")
                .append(complexity)
                .append("_")
                .append(calculatePrice())
                .append("_")
                .append(currency)
                .append("_")
                .append(configuration.resolution.name())
                .append("}").toString();
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public double calculatePrice() {
        return price * configuration.resolution.getCoefficient() * complexity;
    }
}
