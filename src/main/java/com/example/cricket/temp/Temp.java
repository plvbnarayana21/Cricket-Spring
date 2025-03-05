////import java.util.List;
////
////@Data
////@Component
////@Scope("prototype")
////public class Inning {
////
////    @Autowired
////    private OverService overService;
////
////    @Autowired
////    private ScoreCardPrint scoreCardPrint;
////
////    @Autowired
////    private ScoreCardService scoreCardService;
////
////    @Autowired
////    private Probability probability;
////
////    @Autowired
////    private PlayerService playerService;
////
////    private List<Player> battingTeam;
////    private List<Player> bowlingTeam;
////    private int totalRuns = 0;
////    private int wicketsLost = 0;
////    private int ballsBowled = 0;
////    private Player striker;
////    private Player nonStriker;
////    private int bowlerIndex = 10;
////    private int overWickets = 0, overRuns = 0, overnumber = 1;
////    private Innings innings;
////    private int iNo;
////    private Match match;
////
////    public void init(List<Player> battingTeam, List<Player> bowlingTeam, Innings innings, Match match, int iNo) {
////        this.battingTeam = battingTeam;
////        this.innings = innings;
////        this.iNo = iNo;
////        this.match = match;
////        this.bowlingTeam = bowlingTeam;
////        if (battingTeam.size() >= 2) {
////            this.striker = battingTeam.get(0);
////            this.nonStriker = battingTeam.get(1);
////        } else {
////            throw new RuntimeException("Batting team size is not enough ");
////        }
////    }
////
////
////    public int startInnings(int maxOvers, int target) {
////        int flag = 0;
////
////        for(Player pl:bowlingTeam)
////        {
////            System.out.println(pl.getPname());
////        }
////
////        while (ballsBowled < maxOvers * 6 && wicketsLost < 10) {
////            Player bowler = bowlingTeam.get(bowlerIndex);
////            System.out.println("\n=== Over " + overnumber + " ===");
////            bowler.setMatchesPlayed(1);
////
////            overRuns = 0;
////            overWickets = 0;
////
////            for (int ball = 1; ball <= 6; ball++) {
////                if (wicketsLost >= 10) break;
////                striker.setMatchesPlayed(1);
////                BallOutcome outcome = probability.run(striker.getType());
////                BallType ballType = outcome.getBallType();
////                int runs = outcome.getRuns();
////
////                System.out.println("Ball: " + ball + " | " + striker.getPname() + " on strike | Bowler: " + bowler.getPname());
////
////                if (ballType == BallType.WIDE) {
////                    System.out.println("Wide Ball! Extra " + runs + " runs scored.");
////                    totalRuns += runs + 1;
////                    overRuns += runs + 1;
////                    bowler.setRunsConceded(bowler.getRunsConceded() + runs + 1);
////                    ball--;
////                    continue;
////                }
////
////                if (ballType == BallType.NO_BALL) {
////                    System.out.println("No Ball!!!");
////                    totalRuns += 1;
////                    overRuns += 1;
////                    bowler.setRunsConceded(bowler.getRunsConceded() + 1);
////                    ball--;
////                    BallOutcome freeHitOutcome = probability.run(striker.getType());
////                    if (freeHitOutcome.getRuns() != -1) {
////                        System.out.println("Free Hit! Runs scored: " + freeHitOutcome.getRuns());
////                        striker.setRunsScored(striker.getRunsScored() + freeHitOutcome.getRuns());
////                        totalRuns += freeHitOutcome.getRuns();
////                        overRuns += freeHitOutcome.getRuns();
////                        bowler.setRunsConceded(bowler.getRunsConceded() + freeHitOutcome.getRuns());
////                    }
////                    continue;
////                }
////                ballsBowled++;
////                bowler.setBallsBowled(bowler.getBallsBowled() + 1);
////
////                if (runs == 7) {
////                    System.out.println("WICKET! " + striker.getPname() + " is out!");
////                    wicketsLost++;
////                    striker.setBallsFaced(striker.getBallsFaced() + 1);
////                    overWickets++;
////                    bowler.setWicketTaken(bowler.getWicketTaken() + 1);
////                    if (wicketsLost + 1 < battingTeam.size()) {
////                        striker = battingTeam.get(wicketsLost + 1);
////                    }
////
////                } else {
////                    System.out.println("Runs scored: " + runs);
////                    striker.setRunsScored(striker.getRunsScored() + runs);
////                    striker.setBallsFaced(striker.getBallsFaced() + 1);
////                    totalRuns += runs;
////                    overRuns += runs;
////                    bowler.setRunsConceded(bowler.getRunsConceded() + runs);
////
////                    if (runs % 2 == 1) {
////                        Player temp = striker;
////                        striker = nonStriker;
////                        nonStriker = temp;
////                    }
////                }
////
////                if (totalRuns >= target) {
////                    flag = 1;
////                    break;
////                }
////            }
////
////            Player temp = striker;
////            striker = nonStriker;
////            nonStriker = temp;
////
////
////            System.out.println("Over Summary |  over runs: " + overRuns + " | wickets: " + overWickets);
////            overService.updateOver(bowler.getPname(), overRuns, overWickets, overnumber++, iNo, innings, match);
////
////            if (flag == 1) break;
////            bowlerIndex = (bowlerIndex + 10) % bowlingTeam.size();
////        }
////
////        if (flag == 1)
////            System.out.println("\nTarget achieved!!!!");
////
////        System.out.println("\nInnings over! Score: " + totalRuns + "/" + wicketsLost);
////        System.out.println("\nBalls Played:" + ballsBowled);
////
////        scoreCardPrint.displayScore(battingTeam, bowlingTeam, totalRuns, wicketsLost, ballsBowled, match);
////        scoreCardService.update(battingTeam, bowlingTeam, totalRuns, ballsBowled, wicketsLost, innings, match, iNo);
////        return totalRuns;
////    }
////}
//
//
//@Data
//@Component
//@Scope("prototype")
//public class Inning {
//
//    @Autowired
//    private OverService overService;
//
//    @Autowired
//    private ScoreCardPrint scoreCardPrint;
//
//    @Autowired
//    private ScoreCardService scoreCardService;
//
//    @Autowired
//    private ProcessBall processBall;
//
//    private List<Player> battingTeam;
//    private List<Player> bowlingTeam;
//    private int totalRuns = 0;
//    private int wicketsLost = 0;
//    private int ballsBowled = 0;
//    private Player striker;
//    private Player nonStriker;
//    private int bowlerIndex = 10;
//    private int overWickets = 0, overRuns = 0, overnumber = 1;
//    private Innings innings;
//    private int iNo;
//    private Match match;
//
//    public void init(List<Player> battingTeam, List<Player> bowlingTeam, Innings innings, Match match, int iNo) {
//        this.battingTeam = battingTeam;
//        this.innings = innings;
//        this.iNo = iNo;
//        this.match = match;
//        this.bowlingTeam = bowlingTeam;
//        if (battingTeam.size() >= 2) {
//            this.striker = battingTeam.get(0);
//            this.nonStriker = battingTeam.get(1);
//        } else {
//            throw new RuntimeException("Batting team size is not enough ");
//        }
//    }
//
//    public int startInnings(int maxOvers, int target) {
//        int flag = 0;
//
//        while (ballsBowled < maxOvers * 6 && wicketsLost < 10) {
//            Player bowler = bowlingTeam.get(bowlerIndex);
//            System.out.println("\n=== Over " + overnumber + " ===");
//            bowler.setMatchesPlayed(1);
//
//            overRuns = 0;
//            overWickets = 0;
//
//            for (int ball = 1; ball <= 6; ball++) {
//                if (wicketsLost >= 10) break;
//
//                BallOutcome outcome = processBall.processDelivery(striker, bowler,nonStriker);
//
//                if (outcome.getBallType() == BallType.WIDE || outcome.getBallType() == BallType.NO_BALL) {
//                    totalRuns += outcome.getRuns() + (outcome.getBallType() == BallType.WIDE ? 1 : 0);
//                    overRuns += outcome.getRuns() + (outcome.getBallType() == BallType.WIDE ? 1 : 0);
//                    ball--;
//                    continue;
//                }
//
//                ballsBowled++;
//                if (outcome.getRuns() == 7) { // Wicket
//                    wicketsLost++;
//                    overWickets++;
//                    if (wicketsLost + 1 < battingTeam.size()) {
//                        striker = battingTeam.get(wicketsLost + 1);
//                    }
//                } else {
//                    totalRuns += outcome.getRuns();
//                    overRuns += outcome.getRuns();
//                }
//
//                if (totalRuns >= target) {
//                    flag = 1;
//                    break;
//                }
//            }
//
//            Player temp = striker;
//            striker = nonStriker;
//            nonStriker = temp;
//
//            System.out.println("Over Summary |  over runs: " + overRuns + " | wickets: " + overWickets);
//            overService.updateOver(bowler.getPname(), overRuns, overWickets, overnumber++, iNo, innings, match);
//
//            if (flag == 1) break;
//            bowlerIndex = (bowlerIndex + 10) % bowlingTeam.size();
//        }
//
//        if (flag == 1)
//            System.out.println("\nTarget achieved!!!!");
//
//        System.out.println("\nInnings over! Score: " + totalRuns + "/" + wicketsLost);
//        System.out.println("\nBalls Played:" + ballsBowled);
//
//        scoreCardPrint.displayScore(battingTeam, bowlingTeam, totalRuns, wicketsLost, ballsBowled, match);
//        scoreCardService.update(battingTeam, bowlingTeam, totalRuns, ballsBowled, wicketsLost, innings, match, iNo);
//        return totalRuns;
//    }
//}

//
//
//

//@Service
//@RequiredArgsConstructor
//public class MatchService {
//    private static final int OVERS = 10;
//
//    private final InningsRepository inningsRepository;
//    private final TossService tossService;
//    private final TeamRepository teamRepository;
//    private final MatchRepository matchRepository;
//    private final ApplicationContext applicationContext;
//    private final PlayerRepository playerRepository;
//    private final TeamService teamService;
//    private final PlayerMatchService playerMatchService;
//    private final PlayerService playerService;
//
//    public Team battingTeam;
//    public Team bowlingTeam;
//
//    public ResponseEntity<String> startMatch(String team1Name, String team2Name) {
//        Team teamA = teamService.getByname(team1Name);
//        Team teamB = teamService.getByname(team2Name);
//
//        playerService.updatePlayersMatchCount(teamA);
//        playerService.updatePlayersMatchCount(teamB);
//
//        Match match = createAndSaveMatch(teamA, teamB);
//
//        Innings firstInnings = playInnings(match, battingTeam, bowlingTeam, 1, Integer.MAX_VALUE);
//
//        int target = firstInnings.getTotalRuns() + 1;
//        Innings secondInnings = playInnings(match, bowlingTeam, battingTeam, 2, target);
//
//        finalizeMatch(match, firstInnings, secondInnings);
//
//        return ResponseEntity.ok("Match completed successfully");
//    }
//
//
//    public Innings playInnings(Match match, Team battingTeam, Team bowlingTeam,
//                               int inningsNo, int target) {
//
//        Innings innings = Innings.builder()
//                .battingTeam(battingTeam)
//                .bowlingTeam(bowlingTeam)
//                .iNo(inningsNo)
//                .totalRuns(0)
//                .wicketsLost(0)
//                .ballsPlayed(0)
//                .oversToPlay(OVERS)
//                .match(match)
//                .build();
//
//        innings = inningsRepository.save(innings);
//
//        Inning inning = applicationContext.getBean(Inning.class);
//
//        inning.init(battingTeam.getPlayers(), bowlingTeam.getPlayers(), innings, match, inningsNo);
//        int runs = inning.startInnings(10, target);
//
//        innings.setTotalRuns(runs);
//        innings.setWicketsLost(inning.getWicketsLost());
//        innings.setBallsPlayed(inning.getBallsBowled());
//
//        return inningsRepository.save(innings);
//    }
//
//
//    public Match createAndSaveMatch(Team teamA, Team teamB) {
//        TossResult tossResult = tossService.conductToss(teamA, teamB);
//
//        Team tossWinner = tossResult.getTossWinner();
//        String choice = tossResult.getChoice();
//
//        if ("bat".equals(choice)) {
//            battingTeam = tossWinner;
//            bowlingTeam = (tossWinner == teamA) ? teamB : teamA;
//        } else {
//            bowlingTeam = tossWinner;
//            battingTeam = (tossWinner == teamA) ? teamB : teamA;
//        }
//        System.out.println("Batting Team: " + battingTeam.getName());
//        System.out.println("Bowling Team: " + bowlingTeam.getName());
//
//
//        playerService.temp(battingTeam.getPlayers());
//        playerService.temp(bowlingTeam.getPlayers());
//        if (battingTeam.equals(bowlingTeam)) {
//            throw new IllegalStateException("Error! Batting and bowling teams cannot be the same.");
//        }
//
//
//        Match match = Match.builder()
//                .teamA(teamA)
//                .teamB(teamB)
//                .tossWinner(tossWinner.getName())
//                .tossChoice(tossResult.getChoice())
//                .build();
//        return matchRepository.save(match);
//    }
//
//
//    public void finalizeMatch(Match match, Innings firstInnings, Innings secondInnings) {
//        match.setMatchWinner(determineWinner(firstInnings, secondInnings));
//        match.setFirstInnings(firstInnings);
//        match.setSecondInnings(secondInnings);
//        matchRepository.save(match);
//    }
//
//
//    private String determineWinner(Innings firstInnings, Innings secondInnings) {
//        if (firstInnings.getTotalRuns() > secondInnings.getTotalRuns()) {
//            return firstInnings.getBattingTeam().getName();
//        } else if (firstInnings.getTotalRuns() < secondInnings.getTotalRuns()) {
//            return secondInnings.getBattingTeam().getName();
//        } else {
//            return "Draw";
//        }
//    }
//
//}

//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    public Team save(Team team) {
//        return teamRepository.save(team);
//    }
//
//    public Optional<Team> findById(String id) {
//        return teamRepository.findById(id);
//    }
//
//    public List<Team> findAll() {
//        return teamRepository.findAll();
//    }
//
//    public void deleteById(String id) {
//        teamRepository.deleteById(id);
//    }
//
//    public Optional<Team> findByName(String name) {
//        return teamRepository.findByName(name);
//    }

//
//    private final InningsRepository inningsRepository;
//
//    @Autowired
//    public InningsRepo(InningsRepository inningsRepository) {
//        this.inningsRepository = inningsRepository;
//    }
//
//    public Innings save(Innings innings) {
//        return inningsRepository.save(innings);
//    }
//
//    public List<Innings> saveAll(List<Innings> inningsList) {
//        return inningsRepository.saveAll(inningsList);
//    }
//
//    public Optional<Innings> findById(String id) {
//        return inningsRepository.findById(id);
//    }
//
//    public List<Innings> findAll() {
//        return inningsRepository.findAll();
//    }
//
//    public List<Innings> findAllSorted(Sort sort) {
//        return inningsRepository.findAll(sort);
//    }
//
//    public boolean existsById(String id) {
//        return inningsRepository.existsById(id);
//    }
//
//    public long count() {
//        return inningsRepository.count();
//    }
//
//    public void deleteById(String id) {
//        inningsRepository.deleteById(id);
//    }
//
//    public void deleteAll() {
//        inningsRepository.deleteAll();
//    }
//
//    public Optional<Innings> findByMatchIdAndINo(String matchId, int iNo) {
//        List<Innings> inningsList = inningsRepository.findByMatchId(matchId);
//
//        return inningsList.stream()
//                .filter(inning -> inning.getINo() == iNo)
//                .findFirst();
//    }