<?php
/**
 * Template Name: My Tickets Template
 *
 * Description: Twenty Twelve loves the no-sidebar look as much as
 * you do. Use this page template to remove the sidebar from any page.
 *
 * Tip: to remove the sidebar from all posts and pages simply remove
 * any active widgets from the Main Sidebar area, and the sidebar will
 * disappear everywhere.
 *
 * @package WordPress
 * @subpackage Twenty_Twelve
 * @since Twenty Twelve 1.0
 */
get_header();
?>

<div class="main-content">

	<div class="bread">
		<a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;
		<?php the_field('page_title'); ?>
	</div>

	<div class="lhs-menu">
		<?php if ( has_nav_menu( 'self-service-menu' ) || has_nav_menu( 'social' ) ) : ?>
		<div class="menu">
			<?php if ( has_nav_menu( 'self-service-menu' ) ) : ?>
			<?php
        wp_nav_menu(array(
            'theme_location' => 'self-service-menu',
            'menu_class' => 'self-service-menu'
        ));
        ?>
			<?php endif; ?>
		</div>
		<?php endif; ?>
	</div>

	<div class="center-content">
		<div class="content-head">
			<h2>
				<?php the_field('page_title'); ?>
			</h2>
		</div>
		<div id="tickets-list-tickets-panel"
			class="content-middle list-tickets">
			<table class="tbl-tickets" width="100%" cellpadding="0"
				cellspacing="0" border="0">
				<tr>
					<td colspan="5" style="text-align: right;"><select>
							<option selected>Filter</option>
							<option>New</option>
							<option>In Progress</option>
							<option>Complete</option>
					</select>
						<button id="tickets-new-ticket">New Ticket</button>
				
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
			</table>
			<div id="tickets-ticket-table" ></div>
		</div>

		<div id="tickets-view-ticket-panel" class="content-middle view-ticket"
			style="display: none">
			<table class="tbl-tickets" width="100%" cellpadding="0"
				cellspacing="0" border="0">
				<tr>
					<td colspan="5" class="contains-form">Priority: Medium</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form">Category: General</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form">Assignee: Nerdy McNerd</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form">Short Description: Stuff
						doesn't work</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form"><b>Status: In Progress</b></td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr class="tbl-head">
					<td colspan="5">Details</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-text">Lorem ipsum dolor sit amet,
						consectetur adipiscing elit. Praesent bibendum mattis congue.
						Aenean pulvinar ac sapien ut tempus. Class aptent taciti sociosqu
						ad litora torquent per conubia nostra, per inceptos himenaeos.
						Vivamus ornare sem cursus, interdum massa sed, varius dolor. In
						gravida suscipit commodo. Nam non sapien in libero faucibus
						facilisis. Nulla in est efficitur, pharetra ligula ut, porta nibh.
						Quisque ut magna tincidunt enim euismod cursus. Cras risus nibh,
						aliquam eget arcu scelerisque, tristique scelerisque nisl. Cras
						cursus mauris at ullamcorper venenatis. Nullam consectetur tempor
						sagittis. Etiam hendrerit, leo at fringilla ornare, nisl libero
						rhoncus elit, sit amet porttitor mauris neque sed lectus. Nam
						sollicitudin elit tellus, eget aliquam diam viverra vel.</td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr class="tbl-head">
					<td colspan="5">Comments</td>
				</tr>
				<tr>
					<td colspan="5">1. Lorem ipsum dolor sit amet, consectetur
						adipiscing elit.</td>
				</tr>
				<tr>
					<td colspan="5">2. Lorem ipsum dolor sit amet, consectetur
						adipiscing elit.</td>
				</tr>
				<tr>
					<td colspan="5">3. Lorem ipsum dolor sit amet, consectetur
						adipiscing elit.</td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><button>Add Comment</button></td>
					<td colspan="2" style="text-align: right"><button>Back</button></td>
				</tr>
			</table>
		</div>
		<div id="tickets-new-ticket-panel" class="content-middle new-ticket"
			style="display: none">
			<table class="tbl-tickets" width="100%" cellpadding="0"
				cellspacing="0" border="0">
				<tr>
					<td colspan="5" style="text-align: left;"><select>
							<option selected>Priority</option>
							<option>New</option>
							<option>In Progress</option>
							<option>Complete</option>
					</select> <select>
							<option selected>Category</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
					</select>
				
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr class="tbl-head">
					<td colspan="5">Short Description</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form"><input type="text"></td>
				</tr>
				<tr class="tbl-head">
					<td colspan="5">Details</td>
				</tr>
				<tr>
					<td colspan="5" class="contains-form"><textarea rows="5"></textarea></td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><button>Add Attachment</button></td>
					<td colspan="2" style="text-align: right"><button>Cancel</button>
						<button>Submit</button></td>
				</tr>
			</table>
		</div>
		<!-- End posts -->
	</div>

	<div class="rhs-content"></div>


</div>
