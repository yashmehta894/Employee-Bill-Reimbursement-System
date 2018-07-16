import { ERSAngularPage } from './app.po';

describe('ers-angular App', () => {
  let page: ERSAngularPage;

  beforeEach(() => {
    page = new ERSAngularPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
