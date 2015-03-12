package info.joninousiainen.postcode.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import info.joninousiainen.postcode.services.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUI;

@VaadinUI
@Theme("valo")
public class PostcodeUI extends UI {
    @Autowired
    private PostcodeService postcodeService;

    @Override
    protected void init(VaadinRequest request) {
        Page.getCurrent().setTitle("Postcode");

        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);

        int totalUniqueStreetNames = postcodeService.getTotalUniqueStreetNames();
        layout.addComponent(new Label("Total unique street names: " + totalUniqueStreetNames));

        this.setContent(layout);
    }
}