package by.epamtc.jwd.auth.web.tag;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.user_info.IdentityDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

public class JspIdentityDocumentPrinter extends TagSupport {
    private static final Logger logger = LoggerFactory.getLogger
            (JspIdentityDocumentPrinter.class);

    public static final String DOCUMENT_TYPE = "вид документа";
    public static final String SERIES = "серия";
    private static final String DOCUMENT_NUMBER = "номер";
    private static final String PERSONAL_NUMBER = "личный номер";
    private static final String CITIZENSHIP = "гражданство";
    private static final String DATE_OF_ISSUE = "дата выдачи";
    private static final String ISSUING_AUTHORITY = "выдавший орган";
    private static final String DATE_OF_EXPIRY = "срок действия";
    private static final String LATIN_NAMES = "имя и фамилия латинскими буквами";
    private static final String BIRTHDAY = "день рождения";
    private static final String GENDER = "пол";
    private static final String PLACE_OF_ORIGIN = "место происхождения";
    private static final long serialVersionUID = -913942254255445101L;

    private IdentityDocument idDoc;

    public IdentityDocument getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(IdentityDocument idDoc) {
        this.idDoc = idDoc;
    }

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            String description = idDoc.getIdentificationDocumentType()
                    .getDescription();
            String citizenShip = idDoc.getCitizenShip();
            String series = idDoc.getSeries();
            int documentNumber = idDoc.getDocumentNumber();
            String personalNumber = idDoc.getPersonalNumber();
            LocalDate dateOfIssue = idDoc.getDateOfIssue();
            String issueAuthority = idDoc.getIssueAuthority();
            LocalDate dateOfExpiry = idDoc.getDateOfExpiry();
            String latinHolderName = idDoc.getLatinHolderName();
            String latinHolderSurName = idDoc.getLatinHolderSurName();
            LocalDate birthday = idDoc.getBirthday();
            String genderValue = idDoc.getGender().getGenderValue();
            String placeOfOrigin = idDoc.getPlaceOfOrigin();

            out.write(DOCUMENT_TYPE);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(description);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);


            if (series != null){
                out.write(SERIES);
                out.write(AppConstant.COLON);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write(series);
                out.write(AppConstant.COMMA);
                out.write(AppConstant.ONE_WHITESPACE);
            }

            out.write(DOCUMENT_NUMBER);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(String.valueOf(documentNumber));
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(PERSONAL_NUMBER);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(personalNumber);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(CITIZENSHIP);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(citizenShip);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(DATE_OF_ISSUE);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(dateOfIssue.toString());
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(ISSUING_AUTHORITY);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(issueAuthority);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(DATE_OF_EXPIRY);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(dateOfExpiry.toString());
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(LATIN_NAMES);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(latinHolderName);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(latinHolderSurName);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);


            out.write(BIRTHDAY);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(birthday.toString());
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(GENDER);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(genderValue);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write(PLACE_OF_ORIGIN);
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(placeOfOrigin);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

        } catch (IOException e) {
            logger.error("An error while printing in jsp an identity document");
        }

        return SKIP_BODY;
    }
}
