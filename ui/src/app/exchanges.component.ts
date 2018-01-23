import {Component, Input} from '@angular/core';
import {Exchange} from "./models";

const template = `
<ul>
    <li *ngFor="let exchange of exchanges">{{exchange.name}}</li>
</ul>
`;

const style = `

`;


@Component({
  selector: 'gq-trade-exchanges',
  template: template,
  styles: [style]
})
export class ExchangesComponent {

  @Input("exchanges") exchanges: Exchange[];

}
