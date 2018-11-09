package cn.siyueli.platform.zuul.util;

import java.util.regex.Pattern;

public class ProjectMatchUtil {

    public static boolean matchSiyueliMember(String uri) {
        String siyueliMemberUriRegex = "^/api/siyueli-member/[\\s\\S]+$";
        boolean siyueliMemberFlag = Pattern.matches(siyueliMemberUriRegex, uri);
        return siyueliMemberFlag;
    }

    public static boolean matchSiyueliActivity(String uri) {
        String siyueliActivityUriRegex = "^/api/siyueli-activity/[\\s\\S]+$";
        boolean siyueliActivityFlag = Pattern.matches(siyueliActivityUriRegex, uri);
        return siyueliActivityFlag;
    }

    public static boolean matchApiDocs(String uri) {
        boolean apiDocsFlag = Pattern.matches("^/api/[\\s\\S]+/v2/api-docs$", uri);
        return apiDocsFlag;
    }

    public static boolean isSiyueli(String uri) {
        return matchSiyueliMember(uri) || matchSiyueliActivity(uri);
    }
}
