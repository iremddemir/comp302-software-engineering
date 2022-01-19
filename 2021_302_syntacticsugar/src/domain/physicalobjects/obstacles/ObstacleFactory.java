package domain.physicalobjects.obstacles;

import domain.physicalobjects.Vector;

import domain.services.EquipAbilityService;
import domain.services.GameBoardServiceFactory;
import domain.services.ServiceType;
import domain.services.Service;

import java.util.ArrayList;
import java.util.List;

public class ObstacleFactory {
    private static ObstacleFactory instance;

    private ObstacleFactory() {
    }

    public static ObstacleFactory getInstance() {
        if (instance == null) {
            instance = new ObstacleFactory();
        }
        return instance;
    }

    public Obstacle create(ObstacleType type, Vector location, GameBoardServiceFactory gameBoardServiceFactory) {
        List<Service> services = new ArrayList<>();
        services.add(gameBoardServiceFactory.create(ServiceType.DESTROY));

        switch (type) {
            case FirmObstacle:
                return new FirmObstacle(location, services);
            case GiftObstacle:
                services.add(gameBoardServiceFactory.create(ServiceType.SUMMON));
                services.add(gameBoardServiceFactory.create(ServiceType.EQUIP_ABILITY));
                return new GiftObstacle(location, services);
            case ExplosiveObstacle:
                services.add(gameBoardServiceFactory.create(ServiceType.SUMMON));
                return new ExplosiveObstacle(location, services);
            case HollowObstacle:
                services.add(gameBoardServiceFactory.create(ServiceType.SUMMON));
                return new HollowObstacle(location, services);
            default:
                return new SimpleObstacle(location, services);
        }
    }
}
