
I'm used to unit / integration / acceptance testing as opposed to tdd / bdd.
Is bdd essentially equivalent to integration/acceptance testing? 

The way I've been used to writing unit tests is as, e.g., in TitleLookupServicetest - test getJsonResponse().
I use mocks as otherwise dependencies within functions are also being tested?
have created a couple of setter methods in TitleLookupService so that mocks can be used.

I would (if more time etc.) create a new class containing the response with methods to extract data etc.