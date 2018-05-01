<?php
/**
 * The template for displaying an event-category page
 *
 ***************** NOTICE: *****************
 *  Do not make changes to this file. Any changes made to this file
 * will be overwritten if the plug-in is updated.
 *
 * To overwrite this template with your own, make a copy of it (with the same name)
 * in your theme directory. See http://docs.wp-event-organiser.com/theme-integration for more information
 *
 * WordPress will automatically prioritise the template in your theme directory.
 ***************** NOTICE: *****************
 *
 * @package Event Organiser (plug-in)
 * @since 1.0.0
 */

//Call the template header
get_header(); ?>

<div class="main-content">

	<div class="bread"><a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;Events</div>

	<div class="lhs-menu">
	<?php if ( has_nav_menu( 'news-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'news-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'news-menu',
							'menu_class'     => 'news-menu',
						 ) );
					?>
				<?php endif; ?>
			</div>
		<?php endif; ?>
	</div>

	<div class="center-content">

		<div class="content-head">
			<h2>Events</h2>
			<span class="filter">Filter:</span>
			<?php if ( has_nav_menu( 'events' ) || has_nav_menu( 'social' ) ) : ?>
				<div class="filters">
					<?php if ( has_nav_menu( 'events' ) ) : ?>
						<?php
							wp_nav_menu( array(
								'theme_location' => 'events',
								'menu_class'     => 'events-filters',
							 ) );
						?>
					<?php endif; ?>
				</div>
			<?php endif; ?>
		</div>

		<!-- Begin posts -->
		<div class="content-middle">

			<?php eo_get_template_part( 'eo-loop-events' ); //Lists the events ?>

		</div>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		<div class="news-calendar">
			<div class="news-title">
				<h2>Calendar</h2>
				<a class="more" href="<?php echo esc_url( home_url( '/' ) ); ?>events/category/all/">More events</a>
			</div>
			<?php dynamic_sidebar( 'upcoming-events' ); ?>
		</div>
	</div>


</div>

<?php get_footer(); ?>

	

