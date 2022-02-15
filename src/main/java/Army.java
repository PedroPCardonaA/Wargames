import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {
    private final String NAME;
    private final List<Unit> UNITS;

    public Army(String NAME, List<Unit> UNITS) {
        this.NAME = NAME;
        this.UNITS = UNITS;
    }

    public Army(String NAME){
        this.NAME=NAME;
        UNITS = new ArrayList<>();
    }

    public String getNAME() {
        return NAME;
    }
    public void add(Unit unit){
        this.UNITS.add(unit);
    }
    public void addAll(List<Unit> units){
        this.UNITS.addAll(units);
    }
    public void remove(Unit unit){
        this.UNITS.remove(unit);
    }
    public boolean hasUnit(){
        return (!this.UNITS.isEmpty());
    }

    public List<Unit> getAllUnits() {
        return this.UNITS;
    }
    public Unit getRandom(){
        Random random = new Random();
        return this.UNITS.get(random.nextInt(this.UNITS.size()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S"
                ,"NAME","HEALTH","ATTACK TYPE","ATTACK","ARMOR","ATTACK SPEED (PER SECOND)","HIT RATE", "CRITIC RATE", "CRITIC DAMAGE (%)")).append("\n");
        for(Unit unit:UNITS){
            sb.append(unit.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
        return NAME.equals(army.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME);
    }
}
