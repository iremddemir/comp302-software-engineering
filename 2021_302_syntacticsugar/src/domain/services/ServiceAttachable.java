package domain.services;

import java.util.ArrayList;
import java.util.List;

public class ServiceAttachable {
    private final List<Service> services;

    public ServiceAttachable(List<Service> services) {
        if(services == null)
            this.services = new ArrayList<>();
        else
            this.services = new ArrayList<>(services);
    }

    public Service getService(int i){
        return services.get(i);
    }

    public List<Service> getServices(){
        return new ArrayList<>(services);
    }

    public void addService(Service s) {
        services.add(s);
    }
}
