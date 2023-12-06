package string;

import java.util.Stack;

/**
 * Problem Link: https://leetcode.com/problems/simplify-path
 */
public class SimplifyPath {
    // 98.47 on LC
    public String simplifyPath1(String path) {
        StringBuilder sb = new StringBuilder(), sbt = new StringBuilder();
        for (char c : path.toCharArray()) {
            switch (c) {
                case '/':
                    String st = sbt.toString();
                    if (!st.equals("/")) sbt.append(c); // don't make //
                    if (st.equals("/.")) sbt = new StringBuilder("/"); // replace ./ with /
                    else if (st.equals("/..")) { // replace /dir/../ with nothing
                        while (sb.length() > 0 && sb.charAt(sb.length() - 1) != '/') sb.deleteCharAt(sb.length() - 1);
                        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // to remove last /
                        sbt = new StringBuilder("/"); // flush sbt itself
                    }
                    break;
                case '.':
                    sbt.append(c);
                    if (sbt.toString().equals("/.") || sbt.toString().equals("/..")) break;
                    else sbt.deleteCharAt(sbt.length() - 1);
                default: // flush sbt to sb
                    if (sbt.length() > 0) {
                        sb.append(sbt); // append sbt
                        sbt = new StringBuilder(); // flush sbt
                    }
                    sb.append(c);
            }
        }

        if (sbt.toString().equals("/..")) { // replace /dir/../ with nothing
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) != '/') sb.deleteCharAt(sb.length() - 1);
            if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // to remove last /
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    // Using split iterators.
    public String simplifyPath2(String path) {
        String[] chunks = path.split("/");
        Stack<String> chunksSt = new Stack<>();

        for (int i = 0; i < chunks.length; i++) {
            String st = chunks[i];
            if (!st.isEmpty() && !st.equals(".")) {
                if (st.equals("..")) {
                    if (!chunksSt.isEmpty()) chunksSt.pop();
                } else chunksSt.push(st);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : chunksSt) sb.append("/").append(s);
        return sb.length() == 0 ? "/" : sb.toString();
    }

    // 100% on LC; same as above but works it backwards without needing to append and remove in case of /..
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        // this boolean represents whether this char is followed by a slash
        // initialized to true to skip trailing slashes
        boolean slash = true;
        // the count of levels to go up
        int up = 0;
        for (int i = path.length() - 1; i >= 0; i--) {
            char c = path.charAt(i);
            if (c == '/') {
                if (slash) continue; // skip if multiple slashes
                sb.append('/');
                slash = true;
            } else if (c == '.' && slash) {
                char next = path.charAt(i - 1);
                if (next == '/') { // single quote
                    i--; // go to next level
                } else if (next == '.' && path.charAt(i - 2) == '/') { // double quotes
                    up++;
                    i -= 2; // go to next level
                } else { // regular directory name
                    slash = false;
                    i++; // mark this is a name and reprocess
                }
            } else if (up > 0) {
                up--;
                while (path.charAt(i) != '/') i--; // skip this level and go to next level
                slash = true;
            } else {
                slash = false;
                sb.append(c);
            }
        }

        return sb.length() == 0 ? "/" : sb.reverse().toString();
    }

    public static void test() {
        SimplifyPath s = new SimplifyPath();
        assert s.simplifyPath("/home/").equals("/home") : s.simplifyPath("/home/");
        assert s.simplifyPath("/../").equals("/") : s.simplifyPath("/../");
        assert s.simplifyPath("/home//foo/").equals("/home/foo");
        assert s.simplifyPath("/a/./b/../../c/").equals("/c");
        assert s.simplifyPath("/a//b////c/d//././/..").equals("/a/b/c");
        assert s.simplifyPath("/...").equals("/...");
    }
}
