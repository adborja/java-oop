package com.edu.cedesistemas.oop.model.vehicle.race;

public class Main {
    public static void main(String[] args) {
        RaceCar renault = new FormulaOneCar(120, "renault",
                15, "2WD", RaceCar.Team.RENAULT);

        RaceCar mercedes = new FormulaOneCar(110, "mercedes",
                15, "2WD", RaceCar.Team.MERCEDES);

        RaceCar ferrari = new FormulaOneCar(120, "ferrari",
                15, "2WD", RaceCar.Team.FERRARI);

        RaceCar redBull             = new FormulaOneCar(120, "redbull",
                15, "2WD", RaceCar.Team.RED_BULL);

        PitStop<RaceCar> mercedesPS = new PitStop<>(mercedes);
        PitStop<RaceCar> renaultPS = new PitStop<>(renault);
        PitStop<RaceCar> ferrariPS = new PitStop<>(ferrari);
        PitStop<RaceCar> redbullPS = new PitStop<>(redBull);

        Race<RaceCar> race = new Race<>();
        race.addCar(renault);
        race.addCar(mercedes);
        race.addCar(ferrari);
        race.addCar(redBull);

        race.pits(mercedesPS);
        race.pits(renaultPS);
        race.pits(ferrariPS);
        race.pits(redbullPS);

        race.race();
        RaceCar winner = race.getWinner();

        System.out.println("the winner is: " + winner.getName());
    }
}
