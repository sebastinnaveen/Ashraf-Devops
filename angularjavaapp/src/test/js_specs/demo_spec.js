//demo_spec.js //scm test

describe('Demo Browser Test', function() {
	it('should have a valid title', function() {
		
		browser.get(browser.params.url)
		
		expect(browser.getTitle()).toEqual('AngularJavaApp');
	});
});