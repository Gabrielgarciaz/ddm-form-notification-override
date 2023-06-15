package ddm.form.notification.override;

import com.liferay.dynamic.data.mapping.internal.io.DDMFormFieldTypesSerializerTrackerImpl;
import com.liferay.dynamic.data.mapping.internal.notification.DDMFormEmailNotificationSender;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.Portal;

import java.net.URL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
immediate = true, property = "service.ranking:Integer=" + Integer.MAX_VALUE,
service = DDMFormEmailNotificationSender.class
)
public class DDMCustomNotificationSender  extends DDMFormEmailNotificationSender{
	
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	@Reference
	private GroupLocalService _groupLocalService;

	private MailService _mailService;

	@Reference
	private Portal _portal;

	@Reference
	private SoyDataFactory _soyDataFactory;

	private UserLocalService _userLocalService;
	
	@Override
	protected TemplateResource getTemplateResource(String templatePath) {
	    Class<?> clazz = DDMFormEmailNotificationSender.class;

	    ClassLoader classLoader = clazz.getClassLoader();

	    URL templateURL = classLoader.getResource("/META-INF/resources/email/notification.soy");

	    return new URLTemplateResource(templateURL.getPath(), templateURL);
	}
}
