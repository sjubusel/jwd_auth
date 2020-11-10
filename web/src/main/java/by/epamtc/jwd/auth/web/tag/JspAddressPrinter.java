package by.epamtc.jwd.auth.web.tag;

import by.epamtc.jwd.auth.model.constant.AppConstant;
import by.epamtc.jwd.auth.model.user_info.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JspAddressPrinter extends TagSupport {
    private static final Logger logger = LoggerFactory.getLogger
            (JspAddressPrinter.class);
    private static final long serialVersionUID = 6568790582784180964L;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();

        String zipCode = address.getZipCode();
        String country = address.getCountry();
        String region = address.getRegion();
        String area = address.getArea();
        String settlement = address.getSettlement();
        String road = address.getRoad();
        String house = address.getHouse();
        String building = address.getBuilding();
        String room = address.getRoom();

        try {
            out.write("почтовый индекс");
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(zipCode);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write("страна");
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(country);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            if (!settlement.equals(region) && !settlement.equals(area)) {
                out.write("административно-территориальная единица 1 уровня");
                out.write(AppConstant.COLON);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write(region);
                out.write(AppConstant.COMMA);
                out.write(AppConstant.ONE_WHITESPACE);

                out.write("административно-территориальная единица 2 уровня");
                out.write(AppConstant.COLON);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write(area);
                out.write(AppConstant.COMMA);
                out.write(AppConstant.ONE_WHITESPACE);
            }

            out.write("населённый пункт");
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(settlement);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write("дорога");
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(road);
            out.write(AppConstant.COMMA);
            out.write(AppConstant.ONE_WHITESPACE);

            out.write("дом");
            out.write(AppConstant.COLON);
            out.write(AppConstant.ONE_WHITESPACE);
            out.write(house);

            if (building != null) {
                out.write(AppConstant.COMMA);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write("корпус");
                out.write(AppConstant.COLON);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write(building);
            }

            if (room != null) {
                out.write(AppConstant.COMMA);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write("помещение");
                out.write(AppConstant.COLON);
                out.write(AppConstant.ONE_WHITESPACE);
                out.write(room);
            }

        } catch (IOException e) {
            logger.error("An error while printing in jsp an address");
        }

        return SKIP_BODY;
    }
}
