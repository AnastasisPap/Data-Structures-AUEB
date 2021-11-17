public class TagMatching {

    // Returns the tag inside the <>, has as arguments the content of the file and the index of < + 1 if / doesn't follow it otherwise index + 2
    private static String getTag(String content, int i) {
        StringBuilder tag = new StringBuilder();
        // Read until > appears
        while (content.charAt(i) != '>') {
            tag.append(content.charAt(i));
            i++;
        }
        // return the tag
        return tag.toString();
    }

    // returns true if it is matched otherwise false
    private static boolean isMatched(String content) {
        // Stack where tags are stored
        StringStack<String> tagStack = new StringStackImpl<>();

        // read until the end of the file.
        for (int i = 0; i < content.length(); i++) {
            // get current character
            char c = content.charAt(i);

            String tag;
            if (c == '<') {
                // if the next char of < is / increment the index by 2
                if (content.charAt(i + 1) == '/') {
                    // get the closing tag
                    tag = getTag(content, i + 2);
                    // if it's empty or the closing tag is not equal to the top return false
                    if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) return false;
                    else {
                        // otherwise remove the top of the stack
                        tagStack.pop();
                    }

                    // start again from ..>
                    i += tag.length() + 2;
                } else {
                    // otherwise get the opening tag and push it to the top of the stack
                    tag = getTag(content, i + 1);
                    tagStack.push(tag);
                    // the index starts from the >
                    i += tag.length() + 1;
                }
            }
        }

        // if it still has opening tags return false otherwise return true
        return tagStack.isEmpty();
    }

    public static void main(String[] args) {
        // read the content
        String content = ReadFile.readFile(args[0]);
        // if it is matched
        if (isMatched(content)) System.out.println("The given html file has matched tags.");
        // if not matched
        else System.out.println("The given html file does not have matched tags.");
    }
}
