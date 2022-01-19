package domain.listeners;

import domain.services.ServiceType;

public interface ServiceListener {
      void onServicePerformed(ServiceType serviceType, Object serviceInput, Object serviceResult);
}
