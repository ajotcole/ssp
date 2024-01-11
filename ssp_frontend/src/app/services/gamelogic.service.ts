import { ChoiceEnum } from '../models/enums/choiceEnum';
import { WinnerEnum } from '../models/enums/winnerEnum';
import { Round } from '../models/generated/graphql';

export const playRockPaperScissors = (humanChoice: ChoiceEnum) => {
  const choices = Object.values(ChoiceEnum).filter(
    (value) => value !== ChoiceEnum.UNTOUCHED
  );
  const randomIndex = Math.floor(Math.random() * choices.length);
  const computerChoice = choices[randomIndex] as ChoiceEnum;

  let winner: WinnerEnum;

  if (humanChoice === computerChoice) {
    winner = WinnerEnum.TIE;
  } else if (
    (humanChoice === ChoiceEnum.STONE &&
      computerChoice === ChoiceEnum.SCISSOR) ||
    (humanChoice === ChoiceEnum.PAPER && computerChoice === ChoiceEnum.STONE) ||
    (humanChoice === ChoiceEnum.SCISSOR && computerChoice === ChoiceEnum.PAPER)
  ) {
    winner = WinnerEnum.HUMAN;
  } else {
    winner = WinnerEnum.COMPUTER;
  }

  return {
    computerChoice,
    humanChoice,
    winner,
  };
};
