# Inbox

- Decide whether it is safe to delete the `Metrics/` directory from the project. It looks like nobody knows how to generate a new version of this code metrics report.
  - Should we look for a replacement report?
- Convert tabs to spaces and fix formatting.
- Add code to actually let the players answer questions. Why isn't this already done?!
- When can we safely fix the typo "corrent"?!

## Design Ideas

- Game should be able to write to any output stream/Writer.
- Extract some concept of Turn from Board, so that we don't have to worry about which player is "current" nor about advancing to the next player when the Turn ends.
- Add a constructor to be able to create a Game in the desired state: with these players starting on those places with these players in the penalty box....
- 



