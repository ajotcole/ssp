import { Apollo } from 'apollo-angular';
import { Observable } from 'rxjs';
import { Player } from '../../generated/graphql';
import {
  FindAllPlayersQueryDocument,
  FindAllPlayersQueryGQL,
  FindAllPlayersQueryQuery,
} from '../models/generated/graphql';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GraphqlServerService {
  constructor(private apollo: Apollo) {}
}
