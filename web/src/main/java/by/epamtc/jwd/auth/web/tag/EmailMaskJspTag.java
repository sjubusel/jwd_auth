package by.epamtc.jwd.auth.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMaskJspTag extends javax.servlet.jsp.tagext.TagSupport {
    private static final Logger logger = LoggerFactory.getLogger(EmailMaskJspTag
            .class);
    private static final String ASTERISK_REPLACEMENT = "***********";
    private static final long serialVersionUID = 5922424815992723506L;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int doStartTag() {
        // TODO REGEXP
        Pattern pattern = Pattern.compile("@[0-9A-Za-z\\-]+(\\.)[a-z]+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            String emailSuffix = matcher.group();
            int suffixIndex = email.indexOf(emailSuffix);
            email = receiveMaskedEmail(emailSuffix, suffixIndex);
        } else {
            email = ASTERISK_REPLACEMENT;
        }

        JspWriter out = pageContext.getOut();
        try {
            out.write(email);
        } catch (IOException e) {
            logger.error("An error while masking an email. Email=\"{}\"",
                    email);
        }

        return SKIP_BODY;
    }

    private String receiveMaskedEmail(String emailSuffix, int suffixIndex) {
        String emailNaming = email.substring(0, suffixIndex);
        int length = emailNaming.length();
        if (length > 3) {
            return email.substring(0, 1) + ASTERISK_REPLACEMENT
                    + emailNaming.charAt(suffixIndex - 1) + emailSuffix;
        } else {
            switch (length) {
                case 3:
                    return emailNaming.charAt(0) + ASTERISK_REPLACEMENT
                            + emailNaming.charAt(2) + emailSuffix;
                case 2:
                case 1:
                    return ASTERISK_REPLACEMENT + emailSuffix;
            }
        }
        return ASTERISK_REPLACEMENT;
    }
}
