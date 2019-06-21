package com.example.mypc.truyenoffline.utils;

public class Events {
    public static class ActivityFragmentMessage {
 
        private int message;
 
        public ActivityFragmentMessage(int message) {
            this.message = message;
        }
 
        public int getMessage() {
            return message;
        }
    }
}
