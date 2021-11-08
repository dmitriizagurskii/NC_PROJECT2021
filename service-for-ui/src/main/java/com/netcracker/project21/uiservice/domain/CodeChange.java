package com.netcracker.project21.uiservice.domain;

public class CodeChange {
    private String UID;
    private String time;
    private CodeChangeInfo codeChangeInfo;

    public CodeChange(String UID, String time, CodeChangeInfo codeChangeInfo) {
        this.UID = UID;
        this.time = time;

        this.codeChangeInfo = codeChangeInfo;
    }

    public String getTime() {
        return time;
    }

    public String getUID() {
        return UID;
    }

    public CodeChangeInfo getCodeChangeInfo() {
        return codeChangeInfo;
    }

    @Override
    public String toString() {
        return "CodeChange{" +
                "UID='" + UID + '\'' +
                ", time='" + time + '\'' +
                ", codeChangeInfo=" + codeChangeInfo +
                '}';
    }
}
