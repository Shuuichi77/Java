package fr.umlv.fruits;

public enum AppleKind {
    Golden,
    PinkLady,
    GrannySmith;

    @Override
    public String toString() {
        return name().replaceAll("(.)([A-Z])", "$1 $2"); /* Add a space before an uppercase (other than the first one) */
    }
}