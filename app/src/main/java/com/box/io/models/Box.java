package com.box.io.models;

public class Box {
    public int height;
    public int width;
    public int depth;

    public String getSize(){
        StringBuilder builder = new StringBuilder();
        builder.append(height);
        builder.append("cm x ");
        builder.append(width);
        builder.append("cm x ");
        builder.append(depth);
        builder.append("cm ");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Box{" +
                "height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }

}
