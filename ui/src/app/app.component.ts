import { Component } from '@angular/core';

const template = `
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <a class="navbar-brand" href="#">GQTrade</a>
</nav>
<router-outlet></router-outlet>
`;

const style = `

`;


@Component({
  selector: 'gq-trade-root',
  template: template,
  styles: [style]
})
export class AppComponent {
  title = 'gq-trade';
}
