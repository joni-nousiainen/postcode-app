package info.joninousiainen.postcode.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import info.joninousiainen.postcode.model.PostcodeEntry;
import info.joninousiainen.postcode.services.PostcodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUI;
import org.vaadin.spring.annotation.VaadinUIScope;

import java.util.Map;
import java.util.Set;

@VaadinUI
@Theme("valo")
public class PostcodeUI extends UI {
    @Autowired
    private SearchComponent searchComponent;

    @Override
    protected void init(VaadinRequest request) {
        Page.getCurrent().setTitle("Postcode");
        this.setContent(searchComponent);
    }
}

@VaadinComponent
@VaadinUIScope
class SearchComponent extends CustomComponent {
    @Autowired
    private PostcodeService postcodeService;

    private TextField searchField;
    private VerticalLayout searchResultsLayout;

    @Override
    public void attach() {
        super.attach();

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth(100f, Unit.PERCENTAGE);
        mainLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setMargin(new MarginInfo(true, false, true, false));
        searchLayout.setWidth(80f, Unit.PERCENTAGE);
        searchLayout.setSpacing(true);
        mainLayout.addComponent(searchLayout);

        searchField = new TextField();
        searchField.setWidth(100f, Unit.PERCENTAGE);
        searchField.focus();
        searchLayout.addComponent(searchField);

        searchResultsLayout = new VerticalLayout();
        searchResultsLayout.setWidth(80f, Unit.PERCENTAGE);
        searchResultsLayout.setSpacing(true);
        searchResultsLayout.setDefaultComponentAlignment(Alignment.TOP_LEFT);
        mainLayout.addComponent(searchResultsLayout);

        Button searchButton = new Button("Search");
        searchButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        searchButton.addClickListener(e -> searchStreetNames());
        searchLayout.addComponent(searchButton);

        searchLayout.setExpandRatio(searchField, 1f);

        setCompositionRoot(mainLayout);
    }

    private void searchStreetNames() {
        searchResultsLayout.removeAllComponents();

        String query = searchField.getValue();
        if(StringUtils.isNotBlank(query)) {
            postcodeService.getAllPostCodeEntries().stream()
                .filter(entry -> entry.getStreetNameFi().toLowerCase().contains(query.toLowerCase()))
                .forEach(entry -> addToResults(entry));
        }
        else {
            searchResultsLayout.addComponent(new Label("Please enter a street name."));
        }

        searchField.selectAll();
    }

    private void addToResults(PostcodeEntry entry) {
        StringBuilder value = new StringBuilder();
        value.append(entry.getStreetNameFi());
        if (StringUtils.isNotBlank(entry.getStreetNumberMin())) {
            value.append(' ');
            value.append(entry.getStreetNumberMin());
            value.append(" ‒ ");
            value.append(entry.getStreetNumberMax());
        }
        value.append(", ");
        value.append(entry.getPostcode());
        value.append(' ');
        value.append(entry.getPostOfficeFi());
        searchResultsLayout.addComponent(new Label(value.toString().trim()));
    }
}